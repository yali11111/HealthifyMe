package com.example.health2.presentation.ui.viewmodel;

import com.example.health2.domain.usecase.GetStepsUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetStepsUseCase> getStepsUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetStepsUseCase> getStepsUseCaseProvider) {
    this.getStepsUseCaseProvider = getStepsUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getStepsUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<GetStepsUseCase> getStepsUseCaseProvider) {
    return new HomeViewModel_Factory(getStepsUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetStepsUseCase getStepsUseCase) {
    return new HomeViewModel(getStepsUseCase);
  }
}
