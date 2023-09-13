package com.llz.selector;

import com.llz.service.BImportSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String className = BImportSelector.class.getName();
        return new String[]{className};
    }
}
