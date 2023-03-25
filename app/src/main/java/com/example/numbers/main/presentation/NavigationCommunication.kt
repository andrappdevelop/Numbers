package com.example.numbers.main.presentation

import com.example.numbers.numbers.presentation.Communication

interface NavigationCommunication {

    interface Observe : Communication.Observe<NavigationStrategy>

    interface Mutate : Communication.Mutate<NavigationStrategy>

    interface Mutable : Observe, Mutate

    class Base : Communication.SingleUi<NavigationStrategy>(), Mutable
}