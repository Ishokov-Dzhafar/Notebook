package com.dzhafar.lintRules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class CustomLintRulesRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(
            CatchErrorFlowWarning.ISSUE,
            CatchErrorFlow.ISSUE
        )
}