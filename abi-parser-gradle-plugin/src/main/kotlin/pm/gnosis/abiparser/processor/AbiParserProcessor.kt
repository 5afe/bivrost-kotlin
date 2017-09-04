package pm.gnosis.abiparser.processor

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

class AbiParserProcessor: AbstractProcessor() {

    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
    }
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        return false
    }
}