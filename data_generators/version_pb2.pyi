from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class VersionInformation(_message.Message):
    __slots__ = ("major", "minor", "bugfix")
    MAJOR_FIELD_NUMBER: _ClassVar[int]
    MINOR_FIELD_NUMBER: _ClassVar[int]
    BUGFIX_FIELD_NUMBER: _ClassVar[int]
    major: str
    minor: str
    bugfix: str
    def __init__(self, major: _Optional[str] = ..., minor: _Optional[str] = ..., bugfix: _Optional[str] = ...) -> None: ...
