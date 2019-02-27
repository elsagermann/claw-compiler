/*
 * This file is released under terms of BSD license
 * See LICENSE file for more information
 */
package claw.tatsu.xcodeml.xnode.common;

import claw.wani.language.ClawPragma;
import helper.TestConstant;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Test the features of the XcodeProgram class
 *
 * @author clementval
 */

public class XcodeProgTest {

  @Test
  public void basicXcodeProgTest() {
    File f = new File(TestConstant.TEST_DATA);
    assertTrue(f.exists());
    XcodeProgram xcodeml = XcodeProgram.createFromFile(TestConstant.TEST_DATA);
    assertNotNull(xcodeml);
    assertNotNull(xcodeml.getTime());
    assertNotNull(xcodeml.getCompilerInfo());
    assertNotNull(xcodeml.getVersion());
    assertNotNull(xcodeml.getLanguage());
    assertNotNull(xcodeml.getSource());
    assertNotNull(xcodeml.getSourceFileOnly());
    assertEquals("original_code.f90", xcodeml.getSourceFileOnly());
    assertEquals(8, xcodeml.getTypeTable().size());
    assertEquals(2, xcodeml.getGlobalSymbolsTable().size());
    assertEquals(2, xcodeml.getGlobalDeclarationsTable().size());

    // Try to add not meaningful messages in errors
    xcodeml.addError("", 0);
    xcodeml.addError("", null);
    xcodeml.addError("", new ClawPragma());
    assertTrue(xcodeml.getErrors().isEmpty());

    xcodeml.addError("Error1", 1);
    assertEquals(1, xcodeml.getErrors().size());

    // Try to add not meaningful messages in warnings
    xcodeml.addWarning(null, 0);
    xcodeml.addWarning("", 0);
    xcodeml.addWarning(null, Collections.<Integer>emptyList());
    xcodeml.addWarning("", new ClawPragma());
    assertTrue(xcodeml.getWarnings().isEmpty());

    xcodeml.addWarning("New warning 1", 1);
    assertEquals(1, xcodeml.getWarnings().size());
  }

  @Test
  public void creationFailingTest() {
    XcodeProgram xc1 = XcodeProgram.createFromDocument(null);
    assertNotNull(xc1);
    assertTrue(xc1.hasErrors());

    // Simulate STDIN
    ByteArrayInputStream in =
        new ByteArrayInputStream("<dummy></dummy>".getBytes());
    System.setIn(in);

    XcodeProgram xc2 = XcodeProgram.createFromStdInput();
    assertNotNull(xc2);
    assertTrue(xc2.hasErrors());
  }
}
