package fr.motivation.verrouillage;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public final class BootReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context,Intent intent) {
    if(MainActivity.prefs(context).getBoolean("enabled",false))MainActivity.schedule(context);
  }
}
