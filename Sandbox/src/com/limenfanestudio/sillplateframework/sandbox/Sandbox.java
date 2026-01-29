/*
 * File:		Sandbox.java
 * Author:		Limenfane Studio (info@limenfanestudio.com)
 * Created:		2026.01.21
 * Purpose:		The main class of the Sandbox project for testing the Sillplate
 * 				Framework
 */

package com.limenfanestudio.sillplateframework.sandbox;

import com.limenfanestudio.sillplateframework.application.Application;
import com.limenfanestudio.sillplateframework.application
		.ApplicationConfiguration;

public class Sandbox {
	
	public static void main(String[] args) {
		TestScene scene = new TestScene();
		ApplicationConfiguration configuration = new ApplicationConfiguration(
				scene);
		if (Application.Initialize(configuration)) {
			Application.Run();
		}
		if (!Application.Destroy()) {
			System.out.println("Failed to destroy application");
		}
	}
	
}
