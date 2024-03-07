package com.ph.bittelasia.meshtv.tv.carlson.Launcher.Control;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


public class StartAppOnBoot extends BroadcastReceiver {

   public boolean booted=false;

   @Override
   public void onReceive(Context context, Intent intent) {
//      if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//         Intent i = context.getPackageManager().getLaunchIntentForPackage("com.ph.bittelasia.meshtv.tv.staticvlcplayer");
//         String packageString = i.getComponent().getPackageName();
//         String mainActivity = i.getComponent().getClassName();
//         i.addCategory(Intent.CATEGORY_LAUNCHER);
//         i.setComponent(new ComponentName(packageString, mainActivity));
//         i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//         i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//         i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//         booted=true;
//         context.startActivity(i);
//      }
   }
}