    package com.example.traveler.uis.home.trains

    import androidx.lifecycle.ViewModel
    import com.example.traveler.data.repositories.TrainRepository
    import com.example.traveler.util.lazyDeferred

    class TrainViewModel(
        repository: TrainRepository
    ): ViewModel() {
        val trains by lazyDeferred {
            repository.getTrains()
        }
    }