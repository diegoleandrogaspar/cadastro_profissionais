package br.com.diegoleandro.infrastructure.assembler;

import java.util.List;

public interface Converter<T, S, U> {

    public T toDomainObject(U input);

    public S toDto(T domain);

    public List<S> toCollectionDTO(List<T> list);

    public void copyToDomainObject(U input, T type);
}
