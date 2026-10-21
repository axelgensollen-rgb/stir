package fr.motivation.verrouillage;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {
  static final String[] CATEGORY = {"Tous les thèmes", "Motivation", "Argent et réussite", "Sport et discipline", "Citations célèbres"};
  static final String[] THEMES = {"Nuit", "Forêt", "Prune"};
  static final String[] MOTIVATION = {
    "Choisis les personnes qui te choisissent.",
    "La discipline construit ce que la motivation commence.",
    "Chaque jour compte, même les plus difficiles.",
    "Tu n'as pas besoin d'être parfait pour avancer.",
    "Ton avenir se construit aujourd'hui.",
    "Le courage, c'est d'agir malgré le doute.",
    "Continue d'avancer, même lentement.",
    "N'attends pas le bon moment. Crée-le.",
    "La constance dépasse le talent sans discipline.",
    "Protège ta paix et ton énergie.",
    "L'action transforme les intentions en résultats.",
    "Sois fier du chemin, pas seulement du résultat.",
    "Avance chaque jour à ton rythme.",
    "Ne négocie pas avec les excuses.",
    "Ce que tu répètes devient ce que tu es.",
    "Reste fidèle à tes valeurs, pas à tes peurs.",
    "La confiance se construit par l'action.",
    "Tout changement commence par une décision.",
    "Crois en tes actes avant tes paroles.",
    "La patience est aussi une forme de force.",
    "Fais ce que ton futur toi appréciera.",
    "Échouer n'est pas renoncer.",
    "Ta valeur ne dépend pas du regard des autres.",
    "Les petites victoires construisent les grandes.",
    "Avance avec intention, pas avec précipitation.",
    "Investis ton énergie dans ce qui te fait grandir.",
    "Reste humble dans la victoire.",
    "Il n'y a pas de progrès sans inconfort.",
    "Ton rythme n'a pas à ressembler aux autres.",
    "Deviens quelqu'un sur qui tu peux compter.",
    "Chaque effort prouve que tu y crois encore.",
    "La vie change quand tes habitudes changent.",
    "Le respect de soi commence par des limites.",
    "Fais moins de promesses et plus de progrès.",
    "N'oublie jamais pourquoi tu as commencé.",
    "Tes résultats suivent tes actions.",
    "Tu n'as rien à prouver, tout à construire.",
    "Apprends du passé, puis avance.",
    "Construis en silence, laisse parler tes actes.",
    "La meilleure revanche est de réussir ta vie.",
    "Ce qui compte mérite tes efforts.",
    "Prends soin de ce qui compte vraiment.",
    "Une habitude peut changer une vie.",
    "L'estime de soi grandit avec le courage.",
    "Il reste toujours un pas à faire.",
    "La clarté vient après le premier pas.",
    "Une mauvaise journée ne définit pas ton parcours.",
    "L'important, c'est ce que tu fais ensuite.",
    "Reste exigeant et juste avec toi-même.",
    "Ce que tu nourris grandit.",
    "Cherche la progression, pas l'approbation.",
    "Une décision peut tout changer.",
    "Ta discipline d'aujourd'hui crée ta liberté.",
    "Le temps passe. Fais-le compter.",
    "Persévérer, c'est apprendre et recommencer.",
    "Tu mérites des efforts réciproques.",
    "Ne confonds pas attendre et construire.",
    "Choisis un cap, puis avance.",
    "Sois discret et déterminé.",
    "Tu as déjà traversé des jours difficiles."
  };
  static final String[] MONEY = {
    "Construis des actifs, pas seulement un salaire.",
    "L'argent se gère avant de se dépenser.",
    "La liberté financière commence par tes habitudes.",
    "Chaque euro doit avoir une mission.",
    "La richesse se construit par des choix répétés.",
    "La patience est un avantage en investissement.",
    "Fais travailler ton capital avec méthode.",
    "N'achète pas pour impressionner les autres.",
    "Apprends avant de chercher à gagner vite.",
    "La maîtrise du risque précède le gain.",
    "Un objectif sans plan reste une envie.",
    "Épargner aujourd'hui crée des choix demain.",
    "Préfère les progrès durables aux promesses faciles.",
    "Le vrai luxe, c'est de disposer de son temps.",
    "La constance vaut plus que la chance.",
    "Préserve ton capital pour saisir les opportunités.",
    "N'investis pas sous le coup de l'émotion.",
    "Pense en années, pas seulement en journées.",
    "Chaque compétence augmente tes possibilités.",
    "La discipline financière offre plus de liberté."
  };
  static final String[] SPORT = {
    "La dernière répétition commence dans la tête.",
    "Progresse aussi les jours sans motivation.",
    "Chaque séance est un rendez-vous avec toi-même.",
    "La régularité vaut mieux que la perfection.",
    "L'effort quotidien produit le progrès.",
    "Sois plus fort que tes excuses.",
    "Entraîne-toi pour progresser, pas te comparer.",
    "Privilégie la qualité à la précipitation.",
    "La récupération fait partie de l'entraînement.",
    "Reste patient quand les résultats tardent.",
    "La force se construit séance après séance.",
    "La discipline transforme l'envie en habitude.",
    "Tu n'as pas besoin d'être motivé pour commencer.",
    "Répète les bons gestes.",
    "Le progrès se mesure sur la durée.",
    "Respecter ton corps fait partie de la performance.",
    "La constance bat les efforts irréguliers.",
    "Chaque pas compte, même le plus petit.",
    "La régularité fait la différence.",
    "Prépare ta séance et donne le meilleur."
  };
  static final String[][] FAMOUS = {
    {"Patience et longueur de temps font plus que force ni que rage.", "Jean de La Fontaine"},
    {"Rien ne sert de courir ; il faut partir à point.", "Jean de La Fontaine"},
    {"Aide-toi, le Ciel t'aidera.", "Jean de La Fontaine"},
    {"Il faut cultiver notre jardin.", "Voltaire"},
    {"Le cœur a ses raisons que la raison ne connaît point.", "Blaise Pascal"},
    {"Je pense, donc je suis.", "René Descartes"},
    {"La valeur n'attend point le nombre des années.", "Pierre Corneille"},
    {"Un seul être vous manque, et tout est dépeuplé.", "Alphonse de Lamartine"},
    {"Le temps, c'est de l'argent.", "Benjamin Franklin"},
    {"Ce qui ne me tue pas me rend plus fort.", "Friedrich Nietzsche"},
    {"Le travail éloigne de nous trois grands maux : l'ennui, le vice et le besoin.", "Voltaire"},
    {"La fortune sourit aux audacieux.", "Proverbe latin"},
    {"On n'est jamais mieux servi que par soi-même.", "Proverbe"},
    {"C'est en forgeant qu'on devient forgeron.", "Proverbe"},
    {"Vouloir, c'est pouvoir.", "Proverbe"},
    {"Petit à petit, l'oiseau fait son nid.", "Proverbe"},
    {"Après la pluie, le beau temps.", "Proverbe"},
    {"L'union fait la force.", "Proverbe"},
    {"À cœur vaillant rien d'impossible.", "Proverbe"},
    {"Ne remets pas à demain ce que tu peux faire aujourd'hui.", "Proverbe"}
  };

  static SharedPreferences prefs(Context c) { return c.getApplicationContext().getSharedPreferences("options", MODE_PRIVATE); }
  static long today() { return LocalDate.now().toEpochDay(); }
  static final class Quote {
    String text, author;
    Quote(String t, String a) { text=t; author=a; }
  }
  static Quote quote(Context c) {
    SharedPreferences p=prefs(c);
    long index=today()+p.getInt("offset",0);
    int category=p.getInt("category",0);
    if(category==0) category=1+(int)Math.floorMod(index,4L);
    long pos=p.getInt("category",0)==0 ? Math.floorDiv(index,4L) : index;
    if(category==1) return new Quote(MOTIVATION[(int)Math.floorMod(pos,(long)MOTIVATION.length)], "");
    if(category==2) return new Quote(MONEY[(int)Math.floorMod(pos,(long)MONEY.length)], "");
    if(category==3) return new Quote(SPORT[(int)Math.floorMod(pos,(long)SPORT.length)], "");
    String[] item=FAMOUS[(int)Math.floorMod(pos,(long)FAMOUS.length)];
    return new Quote(item[0], item[1]);
  }
  static Bitmap makeWallpaper(Context c) {
    return draw(quote(c),prefs(c).getInt("theme",0));
  }
  static Bitmap draw(Quote q, int theme) {
    final int W=1080, H=2340;
    int t=Math.floorMod(theme,3);
    int[] tops={Color.rgb(8,16,28),Color.rgb(9,28,29),Color.rgb(21,18,29)};
    int[] bottoms={Color.rgb(29,34,47),Color.rgb(11,56,57),Color.rgb(45,29,45)};
    int[] accents={Color.rgb(207,186,148),Color.rgb(154,220,191),Color.rgb(207,174,208)};
    Bitmap b=Bitmap.createBitmap(W,H,Bitmap.Config.ARGB_8888);
    Canvas ca=new Canvas(b);
    Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
    p.setShader(new LinearGradient(0,0,W,H,tops[t],bottoms[t],Shader.TileMode.CLAMP));
    ca.drawRect(0,0,W,H,p); p.setShader(null);
    p.setColor((accents[t]&0x00ffffff)|0x18000000);
    ca.drawCircle(940,1380,460,p);
    p.setColor(accents[t]);p.setStrokeWidth(4);
    ca.drawLine(460,1040,620,1040,p);
    p.setTextAlign(Paint.Align.CENTER);
    p.setTypeface(Typeface.create("sans-serif-medium",Typeface.NORMAL));
    p.setTextSize(30);
    ca.drawText("PENSÉE DU JOUR",W/2f,1110,p);
    TextPaint tp=new TextPaint(Paint.ANTI_ALIAS_FLAG|Paint.SUBPIXEL_TEXT_FLAG);
    tp.setColor(Color.WHITE);tp.setTextAlign(Paint.Align.CENTER);
    tp.setTypeface(Typeface.create("sans-serif-light",Typeface.NORMAL));
    List<String> lines=new ArrayList<>();float size=74f;
    while(size>=42f) {
      tp.setTextSize(size);
      lines=wrap(q.text,tp,900f);
      if(lines.size()<=5)break;
      size-=4f;
    }
    float gap=size*1.35f;
    float first=1460f-(lines.size()-1)*gap/2f;
    for(int i=0;i<lines.size();i++) ca.drawText(lines.get(i),W/2f,first+i*gap,tp);
    if(!q.author.isEmpty()) {
      p.setColor(accents[t]);p.setTextSize(33);
      ca.drawText("— "+q.author,W/2f,1840f,p);
    }
    p.setColor(Color.argb(180,235,235,235));p.setTextSize(23);
    ca.drawText("UN JOUR À LA FOIS",W/2f,1990f,p);
    return b;
  }
  static List<String> wrap(String input, TextPaint paint, float maxWidth) {
    List<String> lines=new ArrayList<>();StringBuilder cur=new StringBuilder();
    for(String word:input.split("\\s+")) {
      String comb=cur.length()==0?word:cur+" "+word;
      if(cur.length()==0||paint.measureText(comb)<=maxWidth)cur=new StringBuilder(comb);
      else {lines.add(cur.toString());cur=new StringBuilder(word);}
    }
    if(cur.length()>0)lines.add(cur.toString());
    return lines;
  }
  static void apply(Context c) throws Exception {
    WallpaperManager wm=WallpaperManager.getInstance(c);
    if(!wm.isWallpaperSupported()||!wm.isSetWallpaperAllowed())
      throw new IllegalStateException("L'accès aux fonds d'écran est interdit.");
    Bitmap img=makeWallpaper(c);
    try{
      wm.setBitmap(img,null,false,WallpaperManager.FLAG_LOCK);
      prefs(c).edit().putLong("lastday",today()).apply();
    } finally {img.recycle();}
  }
  static void ifNeeded(Context c) throws Exception {
    if(prefs(c).getBoolean("enabled",false)&&prefs(c).getLong("lastday",Long.MIN_VALUE)!=today())apply(c);
  }
  static PendingIntent alarmIntent(Context c) {
    Intent i=new Intent(c,DailyReceiver.class);
    return PendingIntent.getBroadcast(c,2044,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  }
  static void schedule(Context c) {
    AlarmManager alarm=(AlarmManager)c.getSystemService(ALARM_SERVICE);
    if(alarm==null)return;
    PendingIntent pending=alarmIntent(c);
    alarm.cancel(pending);
    if(!prefs(c).getBoolean("enabled",false))return;
    Calendar next=Calendar.getInstance();
    next.add(Calendar.DAY_OF_YEAR,1);
    next.set(Calendar.HOUR_OF_DAY,0);
    next.set(Calendar.MINUTE,5);
    next.set(Calendar.SECOND,0);next.set(Calendar.MILLISECOND,0);
    alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,next.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pending);
  }

  int dark=Color.rgb(12,18,29),white=Color.rgb(246,245,242),muted=Color.rgb(180,193,202),accent=Color.rgb(217,190,145);
  ImageView preview; TextView status; Button activate,categoryButton,themeButton;
  volatile boolean busy=false;

  int dp(float value){return (int)(value*getResources().getDisplayMetrics().density+0.5f);}
  TextView text(String str,int sp,int color,boolean bold){
    TextView view=new TextView(this);view.setText(str);view.setTextColor(color);view.setTextSize(sp);
    if(bold)view.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
    return view;
  }
  Button button(LinearLayout parent,String text,boolean primary){
    Button b=new Button(this);b.setAllCaps(false);b.setText(text);
    b.setTextSize(14);b.setTextColor(primary?dark:white);
    GradientDrawable bg=new GradientDrawable();bg.setColor(primary?accent:Color.rgb(35,44,58));bg.setCornerRadius(dp(14));
    b.setBackground(bg);
    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,dp(60));lp.topMargin=dp(10);parent.addView(b,lp);
    return b;
  }
  @Override public void onCreate(Bundle saved){
    super.onCreate(saved);
    getWindow().setStatusBarColor(dark);getWindow().setNavigationBarColor(dark);
    ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);scroll.setBackgroundColor(dark);
    LinearLayout main=new LinearLayout(this);main.setOrientation(1);main.setGravity(Gravity.CENTER_HORIZONTAL);
    main.setPadding(dp(20),dp(50),dp(20),dp(40));scroll.addView(main);
    TextView kicker=text("MOTIVATION • RÉUSSITE • SPORT",12,accent,true);kicker.setGravity(Gravity.CENTER);main.addView(kicker);
    TextView title=text("Une citation. Chaque jour.",28,white,true);
    title.setGravity(Gravity.CENTER);title.setPadding(0,dp(16),0,dp(10));main.addView(title);
    TextView subtitle=text("Directement sur ton écran verrouillé. Gratuit, sans publicité.",14,muted,false);
    subtitle.setGravity(Gravity.CENTER);main.addView(subtitle);
    preview=new ImageView(this);preview.setScaleType(ImageView.ScaleType.FIT_CENTER);
    LinearLayout.LayoutParams pp=new LinearLayout.LayoutParams(dp(220),dp(445));pp.gravity=Gravity.CENTER;pp.topMargin=dp(20);main.addView(preview,pp);
    status=text("",14,muted,false);status.setGravity(Gravity.CENTER);main.addView(status);
    activate=button(main,"Activer la citation quotidienne",true);
    activate.setOnClickListener(v->{
      boolean now=!prefs(this).getBoolean("enabled",false);
      prefs(this).edit().putBoolean("enabled",now).apply();schedule(this);
      if(now)applyAsync("Citation quotidienne activée.");else refresh();
    });
    categoryButton=button(main,"Catégorie",false);
    categoryButton.setOnClickListener(v->{
      SharedPreferences p=prefs(this);p.edit().putInt("category",(p.getInt("category",0)+1)%5).apply();
      refresh();if(p.getBoolean("enabled",false))applyAsync("Catégorie appliquée.");
    });
    Button next=button(main,"Nouvelle citation maintenant",false);
    next.setOnClickListener(v->{
      SharedPreferences p=prefs(this);p.edit().putInt("offset",p.getInt("offset",0)+1).apply();
      refresh();applyAsync("Nouvelle citation sur l'écran verrouillé.");
    });
    themeButton=button(main,"Couleur",false);
    themeButton.setOnClickListener(v->{
      SharedPreferences p=prefs(this);p.edit().putInt("theme",(p.getInt("theme",0)+1)%3).apply();
      refresh();if(p.getBoolean("enabled",false))applyAsync("Couleur appliquée.");
    });
    TextView note=text("Seul ton écran de verrouillage est modifié. Android peut retarder la mise à jour quotidienne pour économiser la batterie.",12,muted,false);
    note.setGravity(Gravity.CENTER);note.setPadding(0,dp(18),0,0);main.addView(note);
    setContentView(scroll);refresh();
  }
  void refresh() {
    Bitmap b=makeWallpaper(this);preview.setImageBitmap(b);
    SharedPreferences p=prefs(this);int c=p.getInt("category",0),t=p.getInt("theme",0);
    categoryButton.setText("Catégorie : "+CATEGORY[c]);themeButton.setText("Couleur : "+THEMES[t]);
    boolean enabled=p.getBoolean("enabled",false);
    status.setText(enabled?"● Changement quotidien activé":"○ Changement quotidien désactivé");
    activate.setText(enabled?"Désactiver le changement quotidien":"Activer la citation quotidienne");
  }
  void applyAsync(String message) {
    if(busy)return;busy=true;status.setText("Modification de l'écran verrouillé…");activate.setEnabled(false);
    new Thread(()->{
      String result=message;boolean ok=true;
      try {apply(this);}catch(Exception e){ok=false;result="Échec : "+e.getMessage();}
      final String msg=result;final boolean success=ok;
      runOnUiThread(()->{
        busy=false;activate.setEnabled(true);refresh();
        if(!success)status.setText(msg);
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
      });
    }).start();
  }
  @Override public void onResume(){super.onResume();new Thread(()->{
    try{ifNeeded(this);}catch(Exception ignored){}
  }).start();}
}
