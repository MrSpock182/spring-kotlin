package br.com.alura.spring.kotlin

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class SpringKotlinApplicationTests {

	@Before
	open fun setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.alura.spring.kotlin")
	}

	@Before
	abstract fun init()
}
