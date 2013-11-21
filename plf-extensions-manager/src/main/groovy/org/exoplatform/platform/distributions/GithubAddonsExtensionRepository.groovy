package org.exoplatform.platform.distributions

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.Method.GET

public class GithubAddonsExtensionRepository implements ExtensionRepository {

  def String getDescription() {
    return "Github eXo Addons"
  }

  def ExtensionRepository.Type getType() {
    return ExtensionRepository.Type.ZIP;
  }

  def String getExtensionLocation(String extensionName) {

    def client = new HTTPBuilder("https://api.github.com/")
    client.setHeaders(Accept: 'application/vnd.github.manifold-preview')

    try {
      def objJsonReleases = null
      client.request(GET, TEXT) { req ->
        uri.path = "/repos/exo-addons/${extensionName}/releases"
        headers.Accept = 'application/vnd.github.manifold-preview'

        response.success = { resp, json ->
          objJsonReleases = new groovy.json.JsonSlurper().parse(json)
        }

        response.failure = { resp ->
          // DO NOTHING
        }
      }

      if(objJsonReleases != null) {
        if(!objJsonReleases.isEmpty()) {
          // get the higher version (currently with alphabetical order of the name, maybe we should use a more deterministic logic)
          def higherVersionRelease = objJsonReleases.sort({it.tag_name}).last()

          if(objJsonReleases.size() > 1) {
            println "    Multiple releases found. Release ${higherVersionRelease} is used."
          }

          def jsonAssets = client.get(uri: higherVersionRelease.assets_url,
                  contentType: TEXT)
          def objJsonAssets = new groovy.json.JsonSlurper().parse(jsonAssets)

          if(objJsonAssets.size() == 1) {
            // TODO Should use the asset url from the response instead, with an Accept header to 'application/octet-stream', but Ant task does not allow to set headers
            return "https://github.com/exo-addons/${extensionName}/releases/download/${higherVersionRelease.tag_name}/${objJsonAssets[0].name}"
          } else if(objJsonAssets.size() > 1) {
            println "    Abort: Extension found but multiple assets for the release ${higherVersionRelease}"
          } else {
            println "    Abort: Extension found but no extension zip for the release ${higherVersionRelease}"
          }
        } else {
          println "    Abort: Extension found but no release for this extension"
        }
      }
    } catch(UnknownHostException uhe) {
      println "    Abort: cannot access api.github.com"
    }

    return null
  }
}