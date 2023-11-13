package com.inetum.appliBibliotheque.converter;

public interface Converter<S,D> {
	
	D map(S source , Class<D> targetClass);
	S mapRetour(D source , Class<S> targetClass);

}
