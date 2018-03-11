package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import java.util.Map;

public class AnalysedClassesCache {

  private final Map<String, AnalysedClass> cache;

  AnalysedClassesCache(final Map<String, AnalysedClass> cache) {
    this.cache = cache;
  }

  public void put(final AnalysedClass analysedClass) {
    cache.put(analysedClass.className(), analysedClass);
  }

  public AnalysedClass get(String selectedClassName) {
    return cache.get(selectedClassName);
  }
}
