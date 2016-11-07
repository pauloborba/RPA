package rpa



import grails.test.mixin.*
import spock.lang.*

@TestFor(ResearchGroupController)
@Mock(ResearchGroup)
class ResearchGroupControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.researchGroupInstanceList
            model.researchGroupInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.researchGroupInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def researchGroup = new ResearchGroup()
            researchGroup.validate()
            controller.save(researchGroup)

        then:"The create view is rendered again with the correct model"
            model.researchGroupInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            researchGroup = new ResearchGroup(params)

            controller.save(researchGroup)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/researchGroup/show/1'
            controller.flash.message != null
            ResearchGroup.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def researchGroup = new ResearchGroup(params)
            controller.show(researchGroup)

        then:"A model is populated containing the domain instance"
            model.researchGroupInstance == researchGroup
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def researchGroup = new ResearchGroup(params)
            controller.edit(researchGroup)

        then:"A model is populated containing the domain instance"
            model.researchGroupInstance == researchGroup
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/researchGroup/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def researchGroup = new ResearchGroup()
            researchGroup.validate()
            controller.update(researchGroup)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.researchGroupInstance == researchGroup

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            researchGroup = new ResearchGroup(params).save(flush: true)
            controller.update(researchGroup)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/researchGroup/show/$researchGroup.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/researchGroup/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def researchGroup = new ResearchGroup(params).save(flush: true)

        then:"It exists"
            ResearchGroup.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(researchGroup)

        then:"The instance is deleted"
            ResearchGroup.count() == 0
            response.redirectedUrl == '/researchGroup/index'
            flash.message != null
    }
}
