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
public final class AppModule_ProvideDefaultDispatcherFactory implements Factory<CoroutineDispatcher> {
  private final Provider<AppDispatchers> dispatchersProvider;

  public AppModule_ProvideDefaultDispatcherFactory(Provider<AppDispatchers> dispatchersProvider) {
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public CoroutineDispatcher get() {
    return provideDefaultDispatcher(dispatchersProvider.get());
  }

  public static AppModule_ProvideDefaultDispatcherFactory create(
      Provider<AppDispatchers> dispatchersProvider) {
    return new AppModule_ProvideDefaultDispatcherFactory(dispatchersProvider);
  }

  public static CoroutineDispatcher provideDefaultDispatcher(AppDispatchers dispatchers) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDefaultDispatcher(dispatchers));
  }
}
