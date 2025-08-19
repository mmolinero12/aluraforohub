-- Ejemplo de modificación de alguna tabla ya existente

ALTER TABLE usuarios ADD activo BOOLEAN;
UPDATE usuarios SET activo = TRUE;