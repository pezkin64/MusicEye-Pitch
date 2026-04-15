package com.xemsoft.sheetmusicscanner2.persist;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

public class Prefs {
    private static final String STRING_KEY_PREFIX = "OptionStringKey_";
    private String PREFS_NAME;
    private Context m_Context;
    private SharedPreferences m_Prefs;

    public Prefs(Context context, String str) {
        this.m_Context = context;
        this.PREFS_NAME = str;
        this.m_Prefs = context.getSharedPreferences(str, 0);
    }

    public void setOptionBool(String str, boolean z) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public boolean getOptionBool(String str, boolean z) {
        return this.m_Prefs.getBoolean(str, z);
    }

    public void setOptionInt(String str, int i) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public int getOptionInt(String str, int i) {
        return this.m_Prefs.getInt(str, i);
    }

    public void setOptionLong(String str, long j) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public long getOptionLong(String str, long j) {
        return this.m_Prefs.getLong(str, j);
    }

    public void setOptionFloat(String str, float f) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        edit.putFloat(str, f);
        edit.commit();
    }

    public float getOptionFloat(String str, float f) {
        return this.m_Prefs.getFloat(str, f);
    }

    public void setOptionString(String str, String str2) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public String getOptionString(String str, String str2) {
        return this.m_Prefs.getString(str, str2);
    }

    public void setOptionLongArray(String str, long[] jArr) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        JSONArray jSONArray = new JSONArray();
        if (jArr != null) {
            jSONArray.put(jArr.length);
            for (long put : jArr) {
                jSONArray.put(put);
            }
            edit.putString(str, jSONArray.toString());
        } else {
            edit.putString(str, (String) null);
        }
        edit.commit();
    }

    public long[] getOptionLongArray(String str) {
        String string = this.m_Prefs.getString(str, (String) null);
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                int i = 0;
                int optInt = jSONArray.optInt(0, 0);
                long[] jArr = new long[optInt];
                while (i < optInt) {
                    int i2 = i + 1;
                    jArr[i] = jSONArray.optLong(i2);
                    i = i2;
                }
                return jArr;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setOptionStringArray(String str, String[] strArr) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        JSONArray jSONArray = new JSONArray();
        if (strArr != null) {
            jSONArray.put(strArr.length);
            for (String put : strArr) {
                jSONArray.put(put);
            }
            edit.putString(str, jSONArray.toString());
        } else {
            edit.putString(str, (String) null);
        }
        edit.commit();
    }

    public String[] getOptionStringArray(String str) {
        String string = this.m_Prefs.getString(str, (String) null);
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                int i = 0;
                int optInt = jSONArray.optInt(0, 0);
                String[] strArr = new String[optInt];
                while (i < optInt) {
                    int i2 = i + 1;
                    strArr[i] = jSONArray.optString(i2);
                    i = i2;
                }
                return strArr;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setOptionIntArrayList(String str, ArrayList<Integer> arrayList) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        JSONArray jSONArray = new JSONArray();
        if (arrayList == null || arrayList.isEmpty()) {
            edit.putString(str, (String) null);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                jSONArray.put(arrayList.get(i));
            }
            edit.putString(str, jSONArray.toString());
        }
        edit.commit();
    }

    public ArrayList<Integer> getOptionIntArrayList(String str) {
        String string = this.m_Prefs.getString(str, (String) null);
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(Integer.valueOf(jSONArray.optInt(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void setOptionStringArrayList(String str, ArrayList<String> arrayList) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        JSONArray jSONArray = new JSONArray();
        if (arrayList == null || arrayList.isEmpty()) {
            edit.putString(str, (String) null);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                jSONArray.put(arrayList.get(i));
            }
            edit.putString(str, jSONArray.toString());
        }
        edit.commit();
    }

    public ArrayList<String> getOptionStringArrayList(String str) {
        String string = this.m_Prefs.getString(str, (String) null);
        ArrayList<String> arrayList = new ArrayList<>();
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    String optString = jSONArray.optString(i, (String) null);
                    if (optString != null) {
                        arrayList.add(optString);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void setOptionUriArrayList(String str, ArrayList<Uri> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList2.add(arrayList.get(i).toString());
            }
        }
        setOptionStringArrayList(str, arrayList2);
    }

    public ArrayList<Uri> getOptionUriArray(String str) {
        ArrayList<String> optionStringArrayList = getOptionStringArrayList(str);
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (int i = 0; i < optionStringArrayList.size(); i++) {
            arrayList.add(Uri.parse(optionStringArrayList.get(i)));
        }
        return arrayList;
    }

    public void setOptionStringSet(String str, Set<String> set) {
        SharedPreferences.Editor edit = this.m_Prefs.edit();
        edit.putStringSet(str, set);
        edit.commit();
    }

    public Set<String> getOptionStringSet(String str) {
        try {
            return this.m_Prefs.getStringSet(str, (Set) null);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public void setOptionKeyString(String str, String str2) {
        setOptionString(STRING_KEY_PREFIX + str, str2);
    }

    public String getOptionKeyString(String str) {
        return getOptionString(STRING_KEY_PREFIX + str, (String) null);
    }
}
