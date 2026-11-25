package com.example.BottomNavMenu.di;

import com.example.health2.core.dispatcher.AppDispatchers;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class AppModule_ProvideMainDispatcherFactory implements Factory<CoroutineDispatcher> {
  private final Provider<AppDispatchers> dispatchersProvider;

  public AppModule_ProvideMainDispatcherFactory(Provider<AppDispatchers> dispatchersProvider) {
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public CoroutineDispatcher get() {
    return provideMainDispatcher(dispatchersProvider.get());
  }

  public static AppModule_ProvideMainDispatcherFactory create(
      Provider<AppDispatchers> dispatchersProvider) {
    return new AppModule_ProvideMainDispatcherFactory(dispatchersProvider);
  }

  public static CoroutineDispatcher provideMainDispatcher(AppDispatchers dispatchers) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMainDispatcher(dispatchers));
  }
}
