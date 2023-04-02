package com.junyoung.cicdgradleproject.common.util

import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.util.EnumSet

@Component
class ProfileUtil(val environment: Environment) {

    fun getActiveProfile(): Profile {
        return Profile.values().firstOrNull { phase ->
            environment.activeProfiles.firstOrNull {
                phase.name == it.uppercase()
            } != null
        } ?: Profile.LOCAL
    }

    fun isLocalProfile(): Boolean = Profile.LOCAL == getActiveProfile()

    fun isProduction() = Profile.PRODUCTION == getActiveProfile()
}

enum class Profile {
    LOCAL,
    DEV,
    SANDBOX,
    BETA,
    PRODUCTION
    ;

    fun toThSysTcd(): String {
        return when (this) {
            DEV -> "D" // 개발
            SANDBOX -> "Q" // 샌박
            BETA, PRODUCTION -> "P" // 운영
            else -> "L" // 로컬
        }
    }
    companion object {
        val TESTABLE_PROFILES: EnumSet<Profile> = EnumSet.of(LOCAL, DEV, SANDBOX)
    }
}
