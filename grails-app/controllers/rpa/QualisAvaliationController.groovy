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
            flash.message = 'Avaliação de períodico não encontrada'
            redirect(controller: 'qualis', action: 'index')
            return
        }
        [qualisAvaliation: qualisAvaliation]
    }
}
