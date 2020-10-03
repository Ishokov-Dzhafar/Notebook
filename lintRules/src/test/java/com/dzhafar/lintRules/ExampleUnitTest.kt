package com.dzhafar.lintRules

import com.android.tools.lint.checks.infrastructure.TestFiles.kt
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `missing catch function on Flow`() {
        lint()
            .allowMissingSdk()
            .files(
                kt(
                    """
                        package test.pkg
                        
                        import kotlinx.coroutines.flow.Flow
                        import kotlinx.coroutines.flow.catch
                        import kotlinx.coroutines.flow.flow
                        
                        class TestClass {
                            val args: Flow<Int> = flow { emit(123) }
                            
                            suspend fun testFun() {
                                args.collect {
                                    print(it)
                                }
                            }
                            
                            fun flowFun(): Flow<Int> = flow{ emit(111) }
                        }
                    """
                )
            )
            .issues(CatchErrorFlow.ISSUE)
            .run()
            .expect("3232")
    }
}