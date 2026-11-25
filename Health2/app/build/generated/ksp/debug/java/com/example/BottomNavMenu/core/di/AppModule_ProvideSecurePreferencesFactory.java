package com.example.BottomNavMenu.core.di;

import android.content.Context;
import com.example.BottomNavMenu.core.security.SecurePreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class AppModule_ProvideSecurePreferencesFactory implements Factory<SecurePreferences> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideSecurePreferencesFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SecurePreferences get() {
    return provideSecurePreferences(contextProvider.get());
  }

  public static AppModule_ProvideSecurePreferencesFactory create(
      Provider<Context> contextProvider) {
    return new AppModule_ProvideSecurePreferencesFactory(contextProvider);
  }

  public static SecurePreferences provideSecurePreferences(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSecurePreferences(context));
  }
}
