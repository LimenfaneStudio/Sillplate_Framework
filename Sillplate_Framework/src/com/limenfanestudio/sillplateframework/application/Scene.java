/*
 * File:		Scene.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.28
 * Purpose:		Defines an abstract scene type in a Sillplate Framework
 * 				application
 */

package com.limenfanestudio.sillplateframework.application;

// An abstract scene of the Sillplate Framework application
public abstract class Scene {
	
	/*
	 * Enter this scene from another
	 * Parameter: Scene lastScene - The last scene displayed by the application
	 * or null if this is the first scene
	 */
	public abstract void enter(Scene lastScene);
	/*
	 * Process user input to this scene
	 * Returns: boolean - Whether the application should continue running
	 */
	public abstract boolean processInput();
	/*
	 * Leave this scene for another
	 * Parameter: Scene nextScene - The next scene to be displayed by the
	 * application or null if this is the final scene
	 */
	public abstract void leave(Scene nextScene);

}
