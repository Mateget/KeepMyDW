# Output a source map file
-printmapping proguard.map
-optimizationpasses 1
-dontoptimize
# Keep filenames and line numbers
-keepattributes SourceFile, LineNumberTable
-dontwarn **
-dontshrink
-keepattributes *Annotation*

-keepclassmembers class keepmydw.auth.WebHandler$1  {
	public *; 
}

-keepclassmembernames public class keepmydw.config.models.** {
    *;
}

-keepclassmembernames public class keepmydw.data.** {
    *;
}

-keep class keepmydw.data.PlayerMountsProvider { 
	public *; 
}

-keep class keepmydw.forge.command.CommandRegister { 
	public *; 
}

-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}




##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

##---------------End: proguard configuration for Gson  ----------