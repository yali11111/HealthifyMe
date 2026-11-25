# ==================================================
# BottomNavMenu - PROGUARD / R8
# ==================================================

# Ne pas desactiver globalement l'optimisation R8.

# Conserver les annotations utilisees par les serializers.
-keepattributes *Annotation*

# Conserver les signatures generiques.
-keepattributes Signature

# Conserver les informations de reflection.
-keepattributes InnerClasses,EnclosingMethod

# Les regles Retrofit, Gson, Room et Hilt
# doivent etre completees selon les versions
# et les bibliotheques effectivement utilisees.

