/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package rules;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for RulesOf6005.
 */
public class RulesOf6005Test {
    
    /**
     * Tests the mayUseCodeInAssignment method.
     */
    @Test
    public void testMayUseCodeInAssignment() {
        assertFalse("Expected false: un-cited publicly-available code",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false));
        assertTrue("Expected true: self-written required code",
                RulesOf6005.mayUseCodeInAssignment(true, false, true, true, true));
    }

    /**
     * Code written by yourself is allowed whatever the other arguments say.
     */
    @Test
    public void testOwnCodeAlwaysAllowed() {
        assertTrue("Expected true: own code, no citation needed",
                RulesOf6005.mayUseCodeInAssignment(true, false, false, false, false));
        assertTrue("Expected true: own code from an earlier assignment",
                RulesOf6005.mayUseCodeInAssignment(true, true, true, false, true));
    }

    /**
     * Cited code that every student can see is allowed when the assignment
     * does not ask you to implement it.
     */
    @Test
    public void testCitedAvailableCodeAllowed() {
        assertTrue("Expected true: cited, available, not course work, not required",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, true, false));
    }

    /**
     * If the assignment says "implement X", external code for X is not allowed,
     * even when it is cited.
     */
    @Test
    public void testImplementationRequiredForbidsExternalCode() {
        assertFalse("Expected false: cited code for a feature we must implement",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, true, true));
    }

    /**
     * Another student's course work is never allowed, cited or not.
     */
    @Test
    public void testOtherStudentsCourseWorkForbidden() {
        assertFalse("Expected false: cited solution written for a 6.005 assignment",
                RulesOf6005.mayUseCodeInAssignment(false, true, true, true, false));
    }

    /**
     * Code that is not available to all students is not allowed.
     */
    @Test
    public void testCodeNotAvailableToAllForbidden() {
        assertFalse("Expected false: cited code that only some students can see",
                RulesOf6005.mayUseCodeInAssignment(false, false, false, true, false));
    }
}
