package TypingAnimation;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


//@State(
//        name = "MagicMode",
//        storages = {@Storage(
//                file = "$APP_CONFIG$/Magic.mode.xml"
//        )}
//)
public class MagicMode implements ApplicationComponent, PersistentStateComponent<MagicMode> {


    @com.intellij.util.xmlb.annotations.Transient
    private ParticleContainerManager particleContainerManager;

    private boolean isEnabled = true;

    public static MagicMode getInstance() {
        return ApplicationManager.getApplication().getComponent(MagicMode.class);
    }

    @Override
    public void initComponent() {

        final EditorActionManager editorActionManager = EditorActionManager.getInstance();
        final EditorFactory editorFactory = EditorFactory.getInstance();
        particleContainerManager = new ParticleContainerManager();
        editorFactory.addEditorFactoryListener(particleContainerManager, new Disposable() {
            @Override
            public void dispose() {

            }
        });
        final TypedAction typedAction = editorActionManager.getTypedAction();
        final TypedActionHandler rawHandler = typedAction.getRawHandler();
        typedAction.setupRawHandler(new TypedActionHandler() {
            @Override
            public void execute(@NotNull final Editor editor, final char c, @NotNull final DataContext dataContext) {
                updateEditor(editor);
                rawHandler.execute(editor, c, dataContext);
            }
        });
    }

    private void updateEditor(@NotNull final Editor editor) {
        //TODO configurable
        particleContainerManager.update(editor);
    }


    @Override
    public void disposeComponent() {
        particleContainerManager.dispose();
        particleContainerManager = null;
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "MagicMode";
    }


    @Nullable
    @Override
    public MagicMode getState() {
        return this;
    }

    @Override
    public void loadState(MagicMode state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnablement(boolean enabled) {
        this.isEnabled = enabled;
    }
}
