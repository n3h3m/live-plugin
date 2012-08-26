/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.intellijeval.toolwindow;

import com.intellij.ide.IdeBundle;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.IconLoader;
import fork.com.intellij.openapi.fileChooser.actions.NewFolderAction;
import org.jetbrains.annotations.NotNull;
import ru.intellijeval.Util;

import javax.swing.*;

/**
 * User: dima
 * Date: 13/08/2012
 */
class NewElementPopupAction extends AnAction implements DumbAware, PopupAction {
	public static final Icon Folder = IconLoader.getIcon("/nodes/folder.png"); // 16x16

	public void actionPerformed(final AnActionEvent event) {
		showPopup(event.getDataContext());
	}

	protected void showPopup(DataContext context) {
		createPopup(context).showInBestPositionFor(context);
	}

	protected ListPopup createPopup(DataContext dataContext) {
		return JBPopupFactory.getInstance().createActionGroupPopup(
				IdeBundle.message("title.popup.new.element"),
				getGroup(),
				dataContext,
				false,
				true,
				false,
				null,
				-1,
				LangDataKeys.PRESELECT_NEW_ACTION_CONDITION.getData(dataContext)
		);
	}

	private ActionGroup getGroup() {
		return new ActionGroup() {
			@NotNull @Override
			public AnAction[] getChildren(AnActionEvent e) {
				return new AnAction[]{
						new NewFileAction("Groovy script", Util.GROOVY_FILE_TYPE_ICON),
						new NewFolderAction("Directory", "Directory", Folder)
				};
			}
		};
	}

	public void update(AnActionEvent e) {
		Presentation presentation = e.getPresentation();
		presentation.setEnabled(true);
	}
}