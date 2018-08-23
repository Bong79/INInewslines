package com.example.ubom.newslines.newsfeed;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;

import com.example.ubom.newslines.R;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    //import android.os.Bundle;
//    import android.preference.PreferenceFragment;
//    import android.support.v7.app.AppCompatActivity;
    public static class NollywoodNewsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference orderby = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderby);

            Preference topic = findPreference(getString(R.string.settings_topic_key));
            bindPreferenceSummaryToValue(topic);
        }

//        @Override
//     1   public boolean onPreferenceChange(Preference preference, Object value) {
//            // The code in this method takes care of updating the displayed preference summary after it has been changed
//            String stringValue = value.toString();
//            preference.setSummary(stringValue);
//
//        }
//      2  public boolean onPreferenceChange(Preference preference, Object value) {
//            String stringValue = value.toString();
//            if (preference instanceof ListPreference) {
//                ListPreference listPreference = (ListPreference) preference;
//                int prefIndex = listPreference.findIndexOfValue(stringValue);
//                if (prefIndex >= 0) {
//                    CharSequence[] labels = listPreference.getEntries();
//                    preference.setSummary(labels[prefIndex]);
//                }
//            } else {
//                preference.setSummary(stringValue);
//            }
//
//            return true;
//        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }
}