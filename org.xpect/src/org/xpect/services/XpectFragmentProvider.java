package org.xpect.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.xpect.XpectFile;
import org.xpect.XpectInvocation;

public class XpectFragmentProvider implements IFragmentProvider {

	protected String getFragment(XpectInvocation obj) {
		return obj.getId();
	}

	public String getFragment(EObject obj, Fallback fallback) {
		if (obj instanceof XpectInvocation)
			return getFragment((XpectInvocation) obj);
		return fallback.getFragment(obj);
	}

	public EObject getEObject(Resource resource, String fragment, Fallback fallback) {
		if (fragment != null && fragment.contains("~"))
			for (EObject obj : resource.getContents())
				if (obj instanceof XpectFile)
					return ((XpectFile) obj).getInvocation(fragment);
		return fallback.getEObject(fragment);
	}

}
