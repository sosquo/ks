package com.llz.service;

public class BImportSelector {

    public void sayHi() {
        System.out.println("2、通过@Import(MyImportSelector.class) => MyImportSelector implements ImportSelector => selectImports 通过返回的全限定类名数组加载类, 无需其他注解");
    }
}
