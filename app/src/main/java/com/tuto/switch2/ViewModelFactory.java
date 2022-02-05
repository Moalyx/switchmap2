package com.tuto.switch2;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tuto.switch2.REPO.TestRepository;
import com.tuto.switch2.UI.list.ListViewModel;
import com.tuto.switch2.UI.main.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory sFactory;
    private final TestRepository testRepository;


    // Pattern singleton : seule la classe elle-même peut s'instancier
    private ViewModelFactory(@NonNull TestRepository testRepository) {
        this.testRepository = testRepository;

    }

    // Pattern singleton : récupération de l'Instance unique disponible partout dans l'app
    public static ViewModelFactory getInstance() {
        if (sFactory == null) {
            synchronized (ViewModelFactory.class) {
                if (sFactory == null) {
                    sFactory = new ViewModelFactory(
                            new TestRepository()
                    );
                }
            }
        }

        return sFactory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(
                    testRepository
            );
        }
        // C'est ici qu'on va créer tous les différents VM : on utilise une seule ViewModelFactory pour toute l'application
        // Exemple pour un deuxième ViewModel :
        else if (modelClass.isAssignableFrom(ListViewModel.class)) {
            return (T) new ListViewModel(
                    testRepository
            );
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}