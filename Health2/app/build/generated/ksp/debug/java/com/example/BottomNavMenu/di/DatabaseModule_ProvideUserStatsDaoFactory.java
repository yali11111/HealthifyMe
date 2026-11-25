package com.example.BottomNavMenu.di;

import com.example.health2.data.local.db.AppDatabase;
import com.example.health2.data.local.db.dao.UserStatsDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class DatabaseModule_ProvideUserStatsDaoFactory implements Factory<UserStatsDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideUserStatsDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserStatsDao get() {
    return provideUserStatsDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideUserStatsDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideUserStatsDaoFactory(databaseProvider);
  }

  public static UserStatsDao provideUserStatsDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideUserStatsDao(database));
  }
}
