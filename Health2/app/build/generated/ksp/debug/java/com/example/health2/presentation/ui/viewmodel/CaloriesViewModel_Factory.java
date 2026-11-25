package com.example.health2.presentation.ui.viewmodel;

import com.example.health2.domain.usecase.CalculateCaloriesUseCase;
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
public final class CaloriesViewModel_Factory implements Factory<CaloriesViewModel> {
  private final Provider<CalculateCaloriesUseCase> calculateCaloriesUseCaseProvider;

  public CaloriesViewModel_Factory(
      Provider<CalculateCaloriesUseCase> calculateCaloriesUseCaseProvider) {
    this.calculateCaloriesUseCaseProvider = calculateCaloriesUseCaseProvider;
  }

  @Override
  public CaloriesViewModel get() {
    return newInstance(calculateCaloriesUseCaseProvider.get());
  }

  public static CaloriesViewModel_Factory create(
      Provider<CalculateCaloriesUseCase> calculateCaloriesUseCaseProvider) {
    return new CaloriesViewModel_Factory(calculateCaloriesUseCaseProvider);
  }

  public static CaloriesViewModel newInstance(CalculateCaloriesUseCase calculateCaloriesUseCase) {
    return new CaloriesViewModel(calculateCaloriesUseCase);
  }
}
