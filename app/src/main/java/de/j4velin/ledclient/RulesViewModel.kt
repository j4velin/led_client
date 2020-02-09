package de.j4velin.ledclient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RulesViewModel : ViewModel() {

    val rules = MutableLiveData<List<Rule>>()
    var ruleRepository: RuleRepository = RuleRepository()
        set(value) {
            field = value
            rules.value = value.rules
        }

    fun addRule(r: Rule) {
        ruleRepository.add(r)
    }

}