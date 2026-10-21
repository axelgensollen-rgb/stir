package fr.motivation.verrouillage;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public final class DailyReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context, Intent intent) {
    PendingResult pending=goAsync();
    new Thread(()->{try{MainActivity.ifNeeded(context.getApplicationContext());}catch(Exception ignored){}finally{pending.finish();}}).start();
  }
}
