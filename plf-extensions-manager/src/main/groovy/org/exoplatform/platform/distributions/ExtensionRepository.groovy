package org.exoplatform.platform.distributions

public interface ExtensionRepository {

  enum Type {
    EXPLODED, ZIP
  }

  def String getDescription()

  def Type getType()

  def String getExtensionLocation(String extensionName)

}