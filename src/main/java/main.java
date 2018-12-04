import controller.ControllerFacade;
import controller.IController;
import model.IModel;
import model.ModelFacade;
import view.IView;
import view.ViewFacade;

public abstract class main {


    /**
     *
     * @param args
     */
    public static void main(final String[] args) {

        final IModel model = new ModelFacade();
        final IView view = new ViewFacade(model);
        final IController controller = new ControllerFacade(view, model);

        controller.start();

    }
}
