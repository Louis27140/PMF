import controller.ControllerFacade;
import controller.IController;
import model.IModel;
import model.ModelFacade;
import view.IView;
import view.ViewFacade;

public abstract class main {
    public static void main(final String[] args) {
        final IModel model = new ModelFacade();
        final IView view = new ViewFacade("Pimp My Fridge", 1000,1000);
        final IController controller = new ControllerFacade(view, model);

        model.addObserver(view);
    }
}
