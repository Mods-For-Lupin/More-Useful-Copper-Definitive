package com.cursee.more_useful_copper;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreUsefulCopper {

  public static final Logger LOG = LoggerFactory.getLogger(Constants.MOD_NAME);

  public static void init() {
  }

  public static Identifier identifier(String path) {
    return Identifier.fromNamespaceAndPath(Constants.MOD_ID, path);
  }
}