/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.jvm.abi;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.GenerateTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("plugins/jvm-abi-gen/testData/compare")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class CompareJvmAbiTestGenerated extends AbstractCompareJvmAbiTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
    }

    public void testAllFilesPresentInCompare() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("plugins/jvm-abi-gen/testData/compare"), Pattern.compile("^([^\\.]+)$"), null, TargetBackend.JVM_IR, false);
    }

    @TestMetadata("anonymousObjects")
    public void testAnonymousObjects() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/anonymousObjects/");
    }

    @TestMetadata("classFlags")
    public void testClassFlags() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/classFlags/");
    }

    @TestMetadata("classPrivateMemebers")
    public void testClassPrivateMemebers() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/classPrivateMemebers/");
    }

    @TestMetadata("clinit")
    public void testClinit() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/clinit/");
    }

    @TestMetadata("constant")
    public void testConstant() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/constant/");
    }

    @TestMetadata("dataClassWithPrivateConstructor")
    public void testDataClassWithPrivateConstructor() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/dataClassWithPrivateConstructor/");
    }

    @TestMetadata("dataClassWithPrivateConstructorWithoutOption")
    public void testDataClassWithPrivateConstructorWithoutOption() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/dataClassWithPrivateConstructorWithoutOption/");
    }

    @TestMetadata("debugInfoLineNumberTable")
    public void testDebugInfoLineNumberTable() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/debugInfoLineNumberTable/");
    }

    @TestMetadata("debugInfoLocalVariableTable")
    public void testDebugInfoLocalVariableTable() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/debugInfoLocalVariableTable/");
    }

    @TestMetadata("debugInfoSourceDebugExtension")
    public void testDebugInfoSourceDebugExtension() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/debugInfoSourceDebugExtension/");
    }

    @TestMetadata("debugInfoSourceFile")
    public void testDebugInfoSourceFile() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/debugInfoSourceFile/");
    }

    @TestMetadata("declarationOrderInline")
    public void testDeclarationOrderInline() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/declarationOrderInline/");
    }

    @TestMetadata("declarationOrderInlineCall")
    public void testDeclarationOrderInlineCall() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/declarationOrderInlineCall/");
    }

    @TestMetadata("declarationOrderPrivateInline")
    public void testDeclarationOrderPrivateInline() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/declarationOrderPrivateInline/");
    }

    @TestMetadata("fieldOrder")
    public void testFieldOrder() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/fieldOrder/");
    }

    @TestMetadata("funOrder")
    public void testFunOrder() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/funOrder/");
    }

    @TestMetadata("functionBody")
    public void testFunctionBody() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/functionBody/");
    }

    @TestMetadata("inlineFunInPrivateClass")
    public void testInlineFunInPrivateClass() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/inlineFunInPrivateClass/");
    }

    @TestMetadata("inlineFunInPrivateNestedClass")
    public void testInlineFunInPrivateNestedClass() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/inlineFunInPrivateNestedClass/");
    }

    @TestMetadata("inlineFunUsageMakesAbiUnstable")
    public void testInlineFunUsageMakesAbiUnstable() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/inlineFunUsageMakesAbiUnstable/");
    }

    @TestMetadata("inlineFunctionBody")
    public void testInlineFunctionBody() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/inlineFunctionBody/");
    }

    @TestMetadata("lambdas")
    public void testLambdas() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/lambdas/");
    }

    @TestMetadata("multifileClass")
    public void testMultifileClass() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/multifileClass/");
    }

    @TestMetadata("nestedPrivateClasses")
    public void testNestedPrivateClasses() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/nestedPrivateClasses/");
    }

    @TestMetadata("parameterName")
    public void testParameterName() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/parameterName/");
    }

    @TestMetadata("preserveDeclarationOrder")
    public void testPreserveDeclarationOrder() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/preserveDeclarationOrder/");
    }

    @TestMetadata("privateInterfaceDefaultImplementation")
    public void testPrivateInterfaceDefaultImplementation() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/privateInterfaceDefaultImplementation/");
    }

    @TestMetadata("privateOuterClassForEffectivelyVisibleInterface")
    public void testPrivateOuterClassForEffectivelyVisibleInterface() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/privateOuterClassForEffectivelyVisibleInterface/");
    }

    @TestMetadata("privateTopLevelClasses")
    public void testPrivateTopLevelClasses() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/privateTopLevelClasses/");
    }

    @TestMetadata("privateTopLevelClassesWithoutOption")
    public void testPrivateTopLevelClassesWithoutOption() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/privateTopLevelClassesWithoutOption/");
    }

    @TestMetadata("privateTypealias")
    public void testPrivateTypealias() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/privateTypealias/");
    }

    @TestMetadata("removeDebugInfo")
    public void testRemoveDebugInfo() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/removeDebugInfo/");
    }

    @TestMetadata("returnType")
    public void testReturnType() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/returnType/");
    }

    @TestMetadata("superClass")
    public void testSuperClass() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/superClass/");
    }

    @TestMetadata("syntheticAccessors")
    public void testSyntheticAccessors() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/syntheticAccessors/");
    }

    @TestMetadata("topLevelPrivateMembers")
    public void testTopLevelPrivateMembers() throws Exception {
        runTest("plugins/jvm-abi-gen/testData/compare/topLevelPrivateMembers/");
    }
}
