package com.example.lab_07

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class DriversScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun goToDrivers() {
        composeTestRule.onNodeWithTag("driversButton").performClick()
    }

    private fun addDriver(name: String) {
        composeTestRule.onNodeWithTag("driverInput").performTextInput(name)
        composeTestRule.onNodeWithTag("addDriverButton").performClick()
    }

    @Test
    fun homeToDriversNavigation() {
        composeTestRule.onNodeWithText("FormulaZero Home").assertIsDisplayed()

        goToDrivers()

        composeTestRule.onNodeWithText("FormulaZero Drivers").assertIsDisplayed()
    }

    @Test
    fun addDriver_isDisplayed() {
        goToDrivers()

        addDriver("Edward Bui")

        composeTestRule.onNodeWithText("Edward Bui").assertIsDisplayed()
    }

    @Test
    fun clearDrivers_removesAllDrivers() {
        goToDrivers()
        addDriver("Edward Bui")
        addDriver("Jalen Brunson")

        composeTestRule.onNodeWithText("Edward Bui").assertIsDisplayed()
        composeTestRule.onNodeWithText("Jalen Brunson").assertIsDisplayed()

        composeTestRule.onNodeWithTag("clearDriversButton").performClick()

        composeTestRule.onNodeWithText("Edward Bui").assertDoesNotExist()
        composeTestRule.onNodeWithText("Jalen Brunson").assertDoesNotExist()
    }

    @Test
    fun backButton_returnsToHome() {
        goToDrivers()
        composeTestRule.onNodeWithText("FormulaZero Drivers").assertIsDisplayed()

        composeTestRule.onNodeWithTag("backButton").performClick()

        composeTestRule.onNodeWithText("FormulaZero Home").assertIsDisplayed()
    }
}