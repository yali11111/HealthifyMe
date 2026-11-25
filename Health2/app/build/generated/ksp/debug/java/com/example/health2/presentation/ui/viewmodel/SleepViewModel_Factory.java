package com.example.health2.presentation.ui.viewmodel;

import com.example.health2.domain.usecase.CalculateSleepUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class SleepViewModel_Factory implements Factory<SleepViewModel> {
  private final Provider<CalculateSleepUseCase> calculateSleepUseCaseProvider;

  public SleepViewModel_Factory(Provider<CalculateSleepUseCase> calculateSleepUseCaseProvider) {
    this.calculateSleepUseCaseProvider = calculateSleepUseCaseProvider;
  }

  @Override
  public SleepViewModel get() {
    return newInstance(calculateSleepUseCaseProvider.get());
  }

  public static SleepViewModel_Factory create(
      Provider<CalculateSleepUseCase> calculateSleepUseCaseProvider) {
    return new SleepViewModel_Factory(calculateSleepUseCaseProvider);
  }

  public static SleepViewModel newInstance(CalculateSleepUseCase calculateSleepUseCase) {
    return new SleepViewModel(calculateSleepUseCase);
  }
}
