package tech.aaregall.lab.function

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

data class Foo(val fooText: String, val fooNumber: Number, val fooBoolean: Boolean)
data class Bar(val barText: String, val barNumber: Number, val barBoolean: Boolean)
data class Baz(val bazText: String, val bazNumber: Number, val bazBoolean: Boolean)

@Configuration
@RegisterReflectionForBinding(classes = [Foo::class, Bar::class, Baz::class])
class Functions {

	@Bean
	fun fooToBar(): (Flux<Foo>) -> Flux<Bar> = {
		it.map { foo -> Bar(foo.fooText, foo.fooNumber, foo.fooBoolean) }
	}

	@Bean
	fun barToBaz(): (Flux<Bar>) -> Flux<Baz> = {
		it.map { bar -> Baz(bar.barText, bar.barNumber, bar.barBoolean) }
	}

}

@SpringBootApplication
class App

fun main(args: Array<String>) {
	runApplication<App>(*args)
}
