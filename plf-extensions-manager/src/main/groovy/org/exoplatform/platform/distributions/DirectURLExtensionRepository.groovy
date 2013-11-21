package org.exoplatform.platform.distributions

public class DirectURLExtensionRepository implements ExtensionRepository {

  def String getDescription() {
    return "direct URL"
  }

  def ExtensionRepository.Type getType() {
    return ExtensionRepository.Type.ZIP;
  }

  def String getExtensionLocation(String extensionName) {
    // valid URL ?
    if (extensionName ==~ /\b(https?|ftp|file):\/\/[-A-Za-z0-9+&@#\/%?=~_|!:,.;]*[-A-Za-z0-9+&@#\/%=~_|]/) {
      return extensionName
    } else {
      return null
    }
  }
}