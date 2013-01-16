
package com.orca.settings.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

import com.orca.settings.R;
import com.orca.settings.R.xml;
import com.orca.settings.SettingsPreferenceFragment;

public class PowerMenu extends SettingsPreferenceFragment {

    private static final String PREF_SCREENSHOT = "show_screenshot";
    private static final String PREF_AIRPLANE_TOGGLE = "show_airplane_toggle";
    private static final String PREF_EXPANDED_DESKTOP = "power_menu_expanded_desktop";
    private static final String PREF_NAVBAR_HIDE = "show_navbar_hide";

    CheckBoxPreference mShowScreenShot;
    CheckBoxPreference mShowAirplaneToggle;
    CheckBoxPreference mExpandedDesktopPref;
    CheckBoxPreference mShowNavBarHide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.power_menu_settings);

        mShowScreenShot = (CheckBoxPreference) findPreference(PREF_SCREENSHOT);
        mShowScreenShot.setChecked(Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.POWER_DIALOG_SHOW_SCREENSHOT,
                0) == 1);

        mShowAirplaneToggle = (CheckBoxPreference) findPreference(PREF_AIRPLANE_TOGGLE);
        mShowAirplaneToggle.setChecked(Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.POWER_DIALOG_SHOW_AIRPLANE_TOGGLE,
                1) == 1);

        mExpandedDesktopPref = (CheckBoxPreference) findPreference(PREF_EXPANDED_DESKTOP);
        mExpandedDesktopPref.setChecked(Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.POWER_MENU_EXPANDED_DESKTOP_ENABLED, 
                1) == 1);

        mShowNavBarHide = (CheckBoxPreference) findPreference(PREF_NAVBAR_HIDE);
        mShowNavBarHide.setChecked(Settings.System.getBoolean(getActivity()
                .getContentResolver(), Settings.System.POWER_DIALOG_SHOW_NAVBAR_HIDE,
                false));

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;

        if (preference == mShowScreenShot) {
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.POWER_DIALOG_SHOW_SCREENSHOT,
                    ((CheckBoxPreference)preference).isChecked() ? 1 : 0);
            return true;
        } else if (preference == mShowAirplaneToggle) {
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.POWER_DIALOG_SHOW_AIRPLANE_TOGGLE,
                    ((CheckBoxPreference)preference).isChecked() ? 1 : 0);
            return true;
        } else if (preference == mExpandedDesktopPref) {
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.POWER_MENU_EXPANDED_DESKTOP_ENABLED,
                    ((CheckBoxPreference)preference).isChecked() ? 1 : 0);
            return true;
        } else if (preference == mShowNavBarHide) {
            Settings.System.putBoolean(getActivity().getContentResolver(),
                    Settings.System.POWER_DIALOG_SHOW_NAVBAR_HIDE,
                    ((CheckBoxPreference)preference).isChecked());
            return true;
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
