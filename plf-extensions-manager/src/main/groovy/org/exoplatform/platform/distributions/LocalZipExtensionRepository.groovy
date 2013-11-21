package org.exoplatform.platform.distributions

public class LocalZipExtensionRepository implements ExtensionRepository {

  def String getDescription() {
    return "local zip"
  }

  def ExtensionRepository.Type getType() {
    return ExtensionRepository.Type.ZIP;
  }

  def String getExtensionLocation(String extensionName) {
    def productHome = new File(System.getProperty("product.home"))

    def extensionsDirectory = new File(productHome, "extensions")

    if (!extensionsDirectory.isDirectory()) {
      return null
    }

    def extensionArchive = new File(extensionsDirectory, extensionName + ".zip");

    if (!extensionArchive.exists()) {
      return null
    }

    return extensionArchive.absolutePath
  }
}