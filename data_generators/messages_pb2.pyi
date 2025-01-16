from google.protobuf.internal import containers as _containers
from google.protobuf.internal import enum_type_wrapper as _enum_type_wrapper
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class ActionType(int, metaclass=_enum_type_wrapper.EnumTypeWrapper):
    __slots__ = ()
    DELETE: _ClassVar[ActionType]
    UPDATE: _ClassVar[ActionType]
    CREATE: _ClassVar[ActionType]
DELETE: ActionType
UPDATE: ActionType
CREATE: ActionType

class VersionInformation(_message.Message):
    __slots__ = ("major", "minor", "bugfix")
    MAJOR_FIELD_NUMBER: _ClassVar[int]
    MINOR_FIELD_NUMBER: _ClassVar[int]
    BUGFIX_FIELD_NUMBER: _ClassVar[int]
    major: str
    minor: str
    bugfix: str
    def __init__(self, major: _Optional[str] = ..., minor: _Optional[str] = ..., bugfix: _Optional[str] = ...) -> None: ...

class MessageList(_message.Message):
    __slots__ = ("messages",)
    MESSAGES_FIELD_NUMBER: _ClassVar[int]
    messages: _containers.RepeatedCompositeFieldContainer[Message]
    def __init__(self, messages: _Optional[_Iterable[_Union[Message, _Mapping]]] = ...) -> None: ...

class Message(_message.Message):
    __slots__ = ("id", "action", "header", "body")
    ID_FIELD_NUMBER: _ClassVar[int]
    ACTION_FIELD_NUMBER: _ClassVar[int]
    HEADER_FIELD_NUMBER: _ClassVar[int]
    BODY_FIELD_NUMBER: _ClassVar[int]
    id: str
    action: ActionType
    header: Header
    body: Body
    def __init__(self, id: _Optional[str] = ..., action: _Optional[_Union[ActionType, str]] = ..., header: _Optional[_Union[Header, _Mapping]] = ..., body: _Optional[_Union[Body, _Mapping]] = ...) -> None: ...

class Header(_message.Message):
    __slots__ = ("createdBy", "timestamp")
    CREATEDBY_FIELD_NUMBER: _ClassVar[int]
    TIMESTAMP_FIELD_NUMBER: _ClassVar[int]
    createdBy: str
    timestamp: str
    def __init__(self, createdBy: _Optional[str] = ..., timestamp: _Optional[str] = ...) -> None: ...

class Body(_message.Message):
    __slots__ = ("id", "name", "phone", "birthdate")
    ID_FIELD_NUMBER: _ClassVar[int]
    NAME_FIELD_NUMBER: _ClassVar[int]
    PHONE_FIELD_NUMBER: _ClassVar[int]
    BIRTHDATE_FIELD_NUMBER: _ClassVar[int]
    id: str
    name: str
    phone: Phone
    birthdate: str
    def __init__(self, id: _Optional[str] = ..., name: _Optional[str] = ..., phone: _Optional[_Union[Phone, _Mapping]] = ..., birthdate: _Optional[str] = ...) -> None: ...

class Phone(_message.Message):
    __slots__ = ("prefix", "number")
    PREFIX_FIELD_NUMBER: _ClassVar[int]
    NUMBER_FIELD_NUMBER: _ClassVar[int]
    prefix: str
    number: str
    def __init__(self, prefix: _Optional[str] = ..., number: _Optional[str] = ...) -> None: ...
