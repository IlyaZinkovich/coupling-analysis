package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import java.util.Map;

class AnalysedClassesCache {

  private final Map<String, AnalysedClass> cache;

  AnalysedClassesCache(final Map<String, AnalysedClass> cache) {
    this.cache = cache;
  }

  void put(final AnalysedClass analysedClass) {
    cache.put(analysedClass.className(), analysedClass);
  }

  AnalysedClass get(final String selectedClassName) {
    return cache.get(selectedClassName);
  }
}
