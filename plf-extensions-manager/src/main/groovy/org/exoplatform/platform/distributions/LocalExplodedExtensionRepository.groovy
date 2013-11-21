package org.exoplatform.platform.distributions

public class LocalExplodedExtensionRepository implements ExtensionRepository {

  def String getDescription() {
    return "local exploded directory"
  }

  def ExtensionRepository.Type getType() {
    return ExtensionRepository.Type.EXPLODED;
  }

  def String getExtensionLocation(String extensionName) {
    def productHome = new File(System.getProperty("product.home"))

    def extensionsDirectory = new File(productHome, "extensions")

    if (!extensionsDirectory.isDirectory()) {
      return null
    }

    def extensionDirectory = new File(extensionsDirectory, extensionName);

    if (!extensionDirectory.isDirectory()) {
      return null
    }

    return extensionDirectory.absolutePath
  }
}