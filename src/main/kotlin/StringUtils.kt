fun String.padStartMultiple(multiple: Int, padChar: Char = ' ') =
        this.padStart(if (this.length % multiple != 0) this.length + multiple - this.length % multiple else 0, padChar)