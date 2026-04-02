package com.example.tictactoe11

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.themeDataStore by preferencesDataStore(name = "theme_preferences")

class ThemePreferences(private val context: Context) {
    val themeStyleFlow: Flow<ThemeStyle> = context.themeDataStore.data.map { preferences ->
        preferences[THEME_STYLE_KEY]?.let { storedValue ->
            runCatching { ThemeStyle.valueOf(storedValue) }.getOrDefault(ThemeStyle.CURRENT_DEFAULT)
        } ?: ThemeStyle.CURRENT_DEFAULT
    }

    suspend fun setThemeStyle(themeStyle: ThemeStyle) {
        context.themeDataStore.edit { preferences ->
            preferences[THEME_STYLE_KEY] = themeStyle.name
        }
    }

    private companion object {
        val THEME_STYLE_KEY = stringPreferencesKey("theme_style")
    }
}