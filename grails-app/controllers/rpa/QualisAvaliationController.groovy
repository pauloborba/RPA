package rpa

class QualisAvaliationController {

    /**
     * Método para o exibir um QualisAvaliation
     * @return
     */
    def show() {
        def qualisAvaliation = QualisAvaliation.findById(params.id)
        if(!qualisAvaliation)
        {
            flash.message = 'qualisAvaliation.not.found.message'
            flash.default = 'Avaliação de qualis não encontrada'
            redirect(controller: 'qualis', action: 'index')
            return
        }
        [qualisAvaliation: qualisAvaliation]
    }
}
